package com.turkcell.library_management_cqrs.core.performance.monitoring;

import com.turkcell.library_management_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_management_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(50)
public class PerformanceBehavior implements PipelineBehavior {

    private static final long THRESHOLD_MS = 3000;

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        String requestName = request.getClass().getSimpleName();
        System.out.println("Simple namei clasin:  " + requestName);
        long startTime = System.currentTimeMillis();

        R response = next.invoke();

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        if (duration > THRESHOLD_MS) {
            System.out.println("[PerformanceBehavior] WARNING: " + requestName + " exceeded threshold. Duration: " + duration + "ms");
        }

        return response;
    }

    @Override
    public boolean supports(Object request) {
        return true; // Tüm request tiplerini izle
    }
}
