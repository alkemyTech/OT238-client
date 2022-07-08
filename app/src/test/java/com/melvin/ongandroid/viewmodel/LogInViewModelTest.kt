package com.melvin.ongandroid.viewmodel

import com.melvin.ongandroid.data.AppData
import com.melvin.ongandroid.domain.use_case.LogInUseCase
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
internal class LogInViewModelTest{


    private lateinit var logInViewModel: LogInViewModel
    private val appData: AppData = mock()
    private val logInUseCase: LogInUseCase = mock()

    @Before
    fun beforeTest(){
        logInViewModel = LogInViewModel(appData, logInUseCase)
    }

    //email tests

    @Test
    fun `wrong email returns false`(){
      val email = "luchoasas.com"
      val result = logInViewModel.validateEmail(email)
      assertEquals(false,result)
    }

    @Test
    fun `email with empty spaces return false`(){
        val email = "lucho asd@gmail.com"
        val result = logInViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `empty email returns false`(){
        val email = ""
        val result = logInViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `null email returns false`(){
        val email:String? = null
        val result = logInViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `valid email returns true`(){
        val email = "lala@string.com"
        val result = logInViewModel.validateEmail(email)
        assertEquals(true,result)
    }

    //password tests

    @Test
    fun `empty password returns false`(){
        val password = ""
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password without a number returns false`(){
        val password = "AaaA@"
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password without lower case letter returns false`(){
        val password = "AAA1A@"
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password without upper case letter returns false`(){
        val password = "aaa1a@"
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password without special character returns false`(){
        val password = "aaa1a"
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password with white space returns false`(){
        val password = "aaa @1a"
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password with less than 4 characters returns false`(){
        val password = "aA@"
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `null password returns false`(){
        val password:String? = null
        val result = logInViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `valid password returns true`(){
        val password = "Aa1@"
        val result = logInViewModel.validatePassword(password)
        assertEquals(true,result)
    }
}
