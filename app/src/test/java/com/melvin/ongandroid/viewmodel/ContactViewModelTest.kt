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

    /**
                            __
                          .'  '.
                     _.-'/  |  \
        ,        _.-"  ,|  /  0 `-.
       |\    .-"       `--""-.__.'=====================-,
       \ '-'`        .___.--._)=========================|
        \            .'       |                         |
        |     /,_.-'          |         NAME            |
      _/   _.'(               |         AND             |
    /  ,-' \  \               |         SURNAME         |
    \  \    `-'               |         TEST            |
    `-'                       '-------------------------'

     */

    @Test
    fun `empty first and last name returns false`(){
        val nameAndSurname = ""
        val result = contactViewModel.validateName(nameAndSurname)
        assertEquals(false, result)
    }

    @Test
    fun `empty first and last name with number return false`(){
        val nameAndSurname = "xavier ros5ales"
        val result = contactViewModel.validateName(nameAndSurname)
        assertEquals(false, result)
    }

    @Test
    fun `only a name return false`(){
        val nameAndSurname = "Xavier"
        val result = contactViewModel.validateName(nameAndSurname)
        assertEquals(false, result)
    }

    /**

    /\_/\
    |°s°|
    |""""\_____/|
    |_|_|________)   EMAIL TEST
    |_|_|...|_|_|

     */

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

    /**

    ░░░░░▄█████▄░░░╔═════════════╔░
    ░░░█▀█▄▄█▄▄█▀█░║ NUMBER TEST ║░
    ░░░▐▄███▒███▄▌═╩═════════════╩░
    ░░░░▀██▀▀▀██▀░░░░░░░░░░░░░░░░░░
    ░░░░░▐█████▌░░░░░░░░░░░░░░░░░░░


     */

    @Test
    fun `empty number returns false`(){
        val number = ""
        val result = contactViewModel.validateNumber(number)
        assertEquals(false,result)
    }

    @Test
    fun `wrong character in number returns false`(){
        val number = "234W23424"
        val result = contactViewModel.validateNumber(number)
        assertEquals(false,result)
    }

    /**

    .......____________________ , ,__
    ....../ `---___________----_____|] - MESSAGE OR QUESTION TEST - ░ ▒▓▓█D
    ...../_==o;;;;;;;;_______.:/
    .....), ---.(_(__) /
    ....// (..) ), ----"
    ...//___//
    ..//___//
    .//___//

     */

    @Test
    fun `empty message returns false`(){
        val message = ""
        val result = contactViewModel.validateNumber(message)
        assertEquals(false,result)
    }
}