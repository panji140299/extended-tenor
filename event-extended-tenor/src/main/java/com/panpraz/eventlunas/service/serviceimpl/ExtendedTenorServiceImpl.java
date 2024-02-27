package com.panpraz.eventlunas.service.serviceimpl;

import com.panpraz.eventlunas.model.extendedtenor.ExtendedTenor;
import com.panpraz.eventlunas.model.extendedtenor.ExtendedTenorRepository;
import com.panpraz.eventlunas.service.ExtendedTenorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtendedTenorServiceImpl implements ExtendedTenorService {
    @Autowired
    ExtendedTenorRepository extendedTenorRepository;

    @Override
    public List<ExtendedTenor> getExtendedTenorEmail() {
        return extendedTenorRepository.getExtendedTenorEmail();
    }

}
