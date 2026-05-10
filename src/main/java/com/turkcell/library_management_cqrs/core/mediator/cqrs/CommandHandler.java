package com.turkcell.library_management_cqrs.core.mediator.cqrs;


//Bu command türü ile işartlenmiş bir class vereceksin, birde geri 
// dönüş tipi vericen ve comman dile uyumlu olacak
public interface CommandHandler<C extends Command<R>, R> 
{
    R handle(C command);
}
