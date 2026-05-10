package com.turkcell.library_management_cqrs.core.mediator;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_management_cqrs.core.mediator.cqrs.QueryHandler;

@Component
public class SpringMediator implements Mediator{
    private final ApplicationContext applicationContext;

    public SpringMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <R> R send(Command<R> command) {
        var handler = (CommandHandler<Command<R>, R>) resolveHandler(command.getClass(), CommandHandler.class);

        return handler.handle(command);
    }

    @Override
    public <R> R send(Query<R> query) {
        var handler = (QueryHandler<Query<R>, R>) resolveHandler(query.getClass(), QueryHandler.class);

        return handler.handle(query);
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

}
