package com.turkcell.library_management_cqrs.core.logging;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_management_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_management_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(100)
public class LoggingBehavior implements PipelineBehavior {

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        String requestName = request.getClass().getSimpleName();

        System.out.println("[LoggingBehavior] Request started: " + requestName);
        System.out.println("[LoggingBehavior] Request data: " + request.toString());

        R response = next.invoke();

        System.out.println("[LoggingBehavior] Response for " + requestName + ": " + response);
        System.out.println("[LoggingBehavior] Request finished: " + requestName);

        return response;
    }

    @Override
    public boolean supports(Object request) {
        return true; // Tüm request tiplerini logla
    }
}
