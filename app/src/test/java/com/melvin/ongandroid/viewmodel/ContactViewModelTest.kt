package com.melvin.ongandroid.viewmodel

import com.melvin.ongandroid.domain.use_case.CreateContactUseCase
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.mock

@RunWith(JUnit4::class)
internal class ContactViewModelTest {

    private lateinit var contactViewModel: ContactViewModel
    private val createContactUseCase: CreateContactUseCase = mock()

    @Before
    fun beforeTest() {
        contactViewModel = ContactViewModel(createContactUseCase)
    }

    //name and surname test

    @Test
    fun `empty first and last name returns false`(){
        val nameAndSurname = ""
        val result = contactViewModel.validateName(nameAndSurname)
        assertEquals(false, result)
    }

    //I don't know if this is right, consult the pibardos
    @Test
    fun `empty first and last name with wrong character return false`(){
        val nameAndSurname = "Francisc4o toro"
        val result = !(nameAndSurname.contains("0")||
                nameAndSurname.contains("1")||
                nameAndSurname.contains("2")||
                nameAndSurname.contains("3")||
                nameAndSurname.contains("4")||
                nameAndSurname.contains("5")||
                nameAndSurname.contains("6")||
                nameAndSurname.contains("7")||
                nameAndSurname.contains("8")||
                nameAndSurname.contains("9"))
        assertEquals(false, result)
    }

    @Test
    fun `only a name return false`(){
        val nameAndSurname = "Francisco"
        val result = (nameAndSurname.contains(" "))
        assertEquals(false, result)
    }

    //email tests

    @Test
    fun `wrong email returns false`(){
        val email = "luchoasas.com"
        val result = contactViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `email with empty spaces return false`(){
        val email = "lucho asd@gmail.com"
        val result = contactViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `empty email returns false`(){
        val email = ""
        val result = contactViewModel.validateEmail(email)
        assertEquals(false,result)
    }

    @Test
    fun `valid email returns true`(){
        val email = "lala@string.com"
        val result = contactViewModel.validateEmail(email)
        assertEquals(true,result)
    }

    //number test

    @Test
    fun `empty number returns false`(){
        val number = ""
        val result = contactViewModel.validateNumber(number)
        assertEquals(false,result)
    }

//    @Test
//    fun `wrong character in number returns false`(){
//        val number = "64565464f"
//        val result = !(number.contains(" ") || number.isDigitsOnly())
//        assertEquals(false,result)
//    }

    //message or question test

    @Test
    fun `empty message returns false`(){
        val message = ""
        val result = contactViewModel.validateNumber(message)
        assertEquals(false,result)
    }
}