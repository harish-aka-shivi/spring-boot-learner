package com.springbootdemo.springbootdemo.service;

import com.springbootdemo.springbootdemo.model.Invoice;
import com.springbootdemo.springbootdemo.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InvoiceService {

    private final String cdnUrl;

    private final InvoiceRepository invoiceRepository;


    public InvoiceService( InvoiceRepository invoiceRepository, @Value("${cdn.url}") String cdnUrl) {
        this.cdnUrl = cdnUrl;
        this.invoiceRepository = invoiceRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
        // TODO download from s3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded templates...");
        // TODO actual deletion of PDFs
    }

    @Transactional
    public Iterable<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);

        return invoiceRepository.save(invoice);
    }

    @Transactional
    public Iterable<Invoice> findByUserId(String userId) {
        return this.invoiceRepository.findByUserId(userId);
    }

}