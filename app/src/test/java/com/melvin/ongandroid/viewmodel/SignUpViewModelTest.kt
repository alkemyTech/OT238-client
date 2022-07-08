package com.melvin.ongandroid.viewmodel

import com.melvin.ongandroid.domain.use_case.RegisterUseCase
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SignUpViewModelTest {

    private lateinit var signUpViewModel: SignUpViewModel
    private val registrationUseCase: RegisterUseCase = mockk()

    @Before
    fun beforeTest(){
        signUpViewModel = SignUpViewModel(registrationUseCase)
    }

    @Test
    fun `wrong email returns false`(){
        val email = "pablo.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false, result)
    }

    @Test
    fun `email with empty spaces return false`(){
        val email = "pablo asd@gmail.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `empty email returns false`(){
        val email = ""
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `null email returns false`(){
        val email: String? = null
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false,result)
    }
    @Test
    fun `valid email returns true`(){
        val email = "lala@string.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(true,result)
    }
    @Test
    fun `password without a number returns false`(){
        val password = "AaaA@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false,result)
    }
    @Test
    fun `password without lower case letter returns false`(){
        val password = "AAA1A@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false,result)
    }
    @Test
    fun `password without upper case letter returns false`(){
        val password = "aaa1a@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false,result)
    }
    @Test
    fun `password with white space returns false`(){
        val password = "aaa @1a"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `password with less than 4 characters returns false`(){
        val password = "aA@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false,result)
    }

    @Test
    fun `null password returns false`(){
        val password:String? = null
        val result = password?.let { signUpViewModel.validatePassword(it) }
        assertEquals(false,result)
    }

    @Test
    fun `valid password returns true`(){
        val password = "Aa1@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(true,result)
    }

    @Test
    fun `entered password does not match return false`(){
        val password = "Aa1@"
        val password2 = "Aaa1@"
        val result = signUpViewModel.confirmPassword(password,password2)
        assertEquals(false,result)
    }
    @Test
    fun `entered password match return true`(){
        val password = "Aa1@"
        val password2 = "Aa1@"
        val result = signUpViewModel.confirmPassword(password,password2)
        assertEquals(true,result)
    }
    @Test
    fun `username is null returns false`(){
        val userName: String? = null
        val result = signUpViewModel.validateUserName(userName)
        assertEquals(false,result)
    }
    @Test
    fun `username is empty returns false`(){
        val userName: String = ""
        val result = signUpViewModel.validateUserName(userName)
        assertEquals(false,result)
    }
    @Test
    fun `valid username returns true    `(){
        val email = "pablopablo"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(true,result)
    }

    @Test
    fun `validate all camps return true`(){
        val userName = "pablo"
        val password = "Aa1@"
        val password2 = "Aa1@"
        val email = "lala@string.com"
        val result = signUpViewModel.validateFields(userName,password,email,password2)
        assertEquals(true,result)
    }

    @Test
    fun `enter a wrong field return null`(){
        val userName = "pablo"
        val password = "Aa1@"
        val password2 = "Aa1@"
        val email = "lala@string.com"
        val result = signUpViewModel.validateFields(userName,password,email,password2)
        assertEquals(true,result)
    }



}