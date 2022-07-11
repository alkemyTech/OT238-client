package com.melvin.ongandroid.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.melvin.ongandroid.MainCoroutinesRule
import com.melvin.ongandroid.data.ApiClient
import com.melvin.ongandroid.data.OngApi
import com.melvin.ongandroid.domain.use_case.RegisterUseCase
import com.melvin.ongandroid.model.entities.AuthMethodsResponse
import com.melvin.ongandroid.model.entities.DataResponse
import com.melvin.ongandroid.model.entities.UserApiResponse
import com.melvin.ongandroid.model.entities.UserRegistrationRequest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SignUpViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @RelaxedMockK
    private lateinit var ongApiMock: OngApi

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var repository: ApiClient
    private lateinit var registrationUseCase: RegisterUseCase


    @Before
    fun beforeTest() {
        MockKAnnotations.init(this)
        repository = ApiClient(ongApiMock)
        registrationUseCase = RegisterUseCase(repository)
        signUpViewModel = SignUpViewModel(registrationUseCase)
    }

    @Test
    fun `wrong email returns false`() {
        val email = "pablo.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false, result)
    }

    @Test
    fun `email with empty spaces return false`() {
        val email = "pablo asd@gmail.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false, result)
    }

    @Test
    fun `empty email returns false`() {
        val email = ""
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false, result)
    }

    @Test
    fun `null email returns false`() {
        val email: String? = null
        val result = signUpViewModel.validateEmail(email)
        assertEquals(false, result)
    }

    @Test
    fun `valid email returns true`() {
        val email = "lala@string.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(true, result)
    }

    @Test
    fun `password without a number returns false`() {
        val password = "AaaA@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false, result)
    }

    @Test
    fun `password without lower case letter returns false`() {
        val password = "AAA1A@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false, result)
    }

    @Test
    fun `password without upper case letter returns false`() {
        val password = "aaa1a@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false, result)
    }

    @Test
    fun `password with white space returns false`() {
        val password = "aaa @1a"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false, result)
    }

    @Test
    fun `password with less than 4 characters returns false`() {
        val password = "aA@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(false, result)
    }

    @Test
    fun `null password returns false`() {
        val password: String? = null
        val result = signUpViewModel.validatePassword(password.toString())
        assertEquals(false, result)
    }

    @Test
    fun `valid password returns true`() {
        val password = "Aa1@"
        val result = signUpViewModel.validatePassword(password)
        assertEquals(true, result)
    }

    @Test
    fun `entered password does not match return false`() {
        val password = "Aa1@"
        val password2 = "Aaa1@"
        val result = signUpViewModel.confirmPassword(password, password2)
        assertEquals(false, result)
    }

    @Test
    fun `entered password match return true`() {
        val password = "Aa1@"
        val password2 = "Aa1@"
        val result = signUpViewModel.confirmPassword(password, password2)
        assertEquals(true, result)
    }

    @Test
    fun `username is null returns false`() {
        val userName: String? = null
        val result = signUpViewModel.validateUserName(userName)
        assertEquals(false, result)
    }

    @Test
    fun `username is empty returns false`() {
        val userName = ""
        val result = signUpViewModel.validateUserName(userName)
        assertEquals(false, result)
    }

    @Test
    fun `valid username returns true`() {
        val email = "a@a.com"
        val result = signUpViewModel.validateEmail(email)
        assertEquals(true, result)
    }

    @Test
    fun `validate all camps return true`() {
        val userName = "pablo"
        val password = "Aa1@"
        val password2 = "Aa1@"
        val email = "lala@string.com"
        val result = signUpViewModel.validateFields(userName, password, email, password2)
        assertEquals(true, result)
    }

    @Test
    fun `enter a wrong field return null`() {
        val userName = "pablo"
        val password = "Aa1@"
        val password2 = "Aa1@"
        val email = "lala@string.com"
        val result = signUpViewModel.validateFields(userName, password, email, password2)
        assertEquals(true, result)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when registrerUser is called response is successful`() =
        runTest {
            coEvery {
                repository.registerUser(newUser)
            } returns authMethodsResponse()
            registrationUseCase.registerUser(newUser)
            Assert.assertEquals(true, signUpViewModel.status.value)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when registrerUser is called response is failure`() =
        runTest {
            coEvery {
                repository.registerUser(newUser)
            } returns authMethodsResponseFalse()
            registrationUseCase.registerUser(newUser)
            assertEquals(statusError(), signUpViewModel._status.value)
        }


    private fun authMethodsResponse(): AuthMethodsResponse =
        AuthMethodsResponse(true, dataResponse(), "asd")

    private fun authMethodsResponseFalse(): AuthMethodsResponse =
        AuthMethodsResponse(false, dataResponse(), "asd")

    private fun dataResponse(): DataResponse = DataResponse(userApiResponse(), "123")
    private fun userApiResponse(): UserApiResponse = UserApiResponse(1, "pablo", "a@a.com", "aA@1")

    //Api Status
    private fun statusSuccess(): ApiStatus = ApiStatus.SUCCESS
    private fun statusError(): ApiStatus = ApiStatus.FAILURE

    private val newUser = UserRegistrationRequest("pablo", "a@a.com", "Aa@1")


}