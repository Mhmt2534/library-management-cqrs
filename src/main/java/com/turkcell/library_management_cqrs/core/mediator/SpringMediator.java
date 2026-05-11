package com.turkcell.library_management_cqrs.core.mediator;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_management_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_management_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
public class SpringMediator implements Mediator{
    private final ApplicationContext applicationContext;
    private final List<PipelineBehavior> behaviors;

    public SpringMediator(ApplicationContext applicationContext,List<PipelineBehavior> behaviors) {
        this.applicationContext = applicationContext;
        this.behaviors = behaviors.stream().sorted(AnnotationAwareOrderComparator.INSTANCE).toList();
    }

    @Override
    public <R> R send(Command<R> command) {
        var handler = (CommandHandler<Command<R>, R>) resolveHandler(command.getClass(), CommandHandler.class);

       return invokePipeline(command, () -> handler.handle(command));
    }

    @Override
    public <R> R send(Query<R> query) {
        var handler = (QueryHandler<Query<R>, R>) resolveHandler(query.getClass(), QueryHandler.class);

        return invokePipeline(query, () -> handler.handle(query));
    }



    private Object resolveHandler(Class<?> requestType, Class<?> handlerInterface){
        
        // Java rumtime da generic tür bilgisi silindiği için, handler
        //  arayüzünü uygulayan bean'leri bulmak için Spring'in ResolvableType
        //  özelliğini kullanarak generic tür bilgisine erişiyoruz.
        
        // Belirtilen handler arayüzünü uygulayan tüm bean'leri al
        String[] beans = applicationContext.getBeanNamesForType(handlerInterface);

        // Her bir bean'i kontrol et ve istenen request türünü işleyen handler'ı bul
         for (String beanName : beans) {
        Object bean = applicationContext.getBean(beanName);

        // Bean'in handler arayüzünü uygulayıp uygulamadığını kontrol et
        ResolvableType type = ResolvableType.forClass(bean.getClass()).as(handlerInterface);

        Class<?> handledRequestType = type.getGeneric(0).resolve();

        if (handledRequestType != null && handledRequestType.equals(requestType)) {
            return bean;
        }
    }
    

    throw new IllegalStateException("Handler bulunamadı: " + requestType.getSimpleName());
    }

     private <R> R invokePipeline(Object request, RequestHandlerDelegate<R> handlerInvocation)
    {
        RequestHandlerDelegate<R> next = handlerInvocation; // Handler'ın kendisi

        for(int i = behaviors.size() - 1; i >= 0; i--) // Sıraya göre tersten Behaviorları çağır.
        {
            PipelineBehavior behavior = behaviors.get(i);
            if(!behavior.supports(request)) continue;

            RequestHandlerDelegate<R> current = next;
            next = () -> behavior.handle(request, current);
        }
        // döngü bitti.

        return next.invoke(); // handlerın kendisi
    }


}
