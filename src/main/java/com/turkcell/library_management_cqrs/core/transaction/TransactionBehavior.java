package com.turkcell.library_management_cqrs.core.transaction;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.turkcell.library_management_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_management_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(10)
public class TransactionBehavior implements PipelineBehavior {

    private final TransactionTemplate transactionTemplate;

    public TransactionBehavior(PlatformTransactionManager transactionManager) {
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        String requestName = request.getClass().getSimpleName();

        System.out.println("[TransactionBehavior] Starting transaction for: " + requestName);

        try {
            R result = transactionTemplate.execute(new TransactionCallback<R>() {
                @Override
                public R doInTransaction(TransactionStatus status) {
                    return next.invoke();
                }
            });

            System.out.println("[TransactionBehavior] Transaction committed for: " + requestName);
            return result;

        } catch (Exception e) {
            System.out.println("[TransactionBehavior] Transaction rolled back for: " + requestName + " due to: " + e.getMessage());
            throw e; // Exception'ı yeniden fırlat
        }
    }

    @Override
    public boolean supports(Object request) {
        return request.getClass().getSimpleName().endsWith("Command");
    }
}
