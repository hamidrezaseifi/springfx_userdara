package com.seifi.springfx_userdara.controllers;

import com.seifi.springfx_userdara.services.IUserDataService;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;

public class TestUserFormViewController {

    @InjectMocks
    UserFormViewController userFormViewController;

    @Mock
    IUserDataService userDataService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    public void testLoadUserAdditionInfo(){

    }

}
