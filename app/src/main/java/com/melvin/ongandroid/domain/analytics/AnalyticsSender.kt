package com.melvin.ongandroid.domain.analytics

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

class AnalyticsSender {

    companion object {

        private const val EVENT_LAST_NEWS_SEE_MORE_PRESSED = "last_news_see_more_pressed"
        private const val EVENT_TESTIMONIALS_SEE_MORE_PRESSED = "testimonies_see_more_pressed"
        private const val EVENT_SLIDER_RETRIEVE_SUCCESS = "slider_retrieve_success"
        private const val EVENT_SLIDER_RETRIEVE_ERROR = "slider_retrieve_error"
        private const val EVENT_NEWS_RETRIEVE_SUCCESS = "last_news_retrieve_success"
        private const val EVENT_NEWS_RETRIEVE_ERROR = "last_news_retrieve_error"
        private const val EVENT_TESTIMONIALS_RETRIEVE_SUCCESS = "testimonials_retrieve_success"
        private const val EVENT_TESTIMONIALS_RETRIEVE_ERROR = "testimonials_retrieve_error"
        private const val EVENT_LOG_IN_PRESSED = "log_in_pressed"
        private const val EVENT_SIGN_UP_PRESSED = "sign_up_pressed"
        private const val EVENT_GOOGLE_PRESSED = "google_pressed"
        private const val EVENT_FACEBOOK_PRESSED = "facebook_pressed"
        private const val EVENT_LOG_IN_SUCCESS = "log_in_success"
        private const val EVENT_LOG_IN_ERROR = "log_in_error"

        fun trackSignUpPressed(press: String){
            sendCustomEvent(EVENT_SIGN_UP_PRESSED, hashMapOf("press" to press ))
        }
        fun trackGooglePressed(press: String){
            sendCustomEvent(EVENT_GOOGLE_PRESSED, hashMapOf("press" to press ))
        }
        fun trackLogInPressed(press: String){
            sendCustomEvent(EVENT_LOG_IN_PRESSED, hashMapOf("press" to press ))
        }
        fun trackFacebookPressed(press: String){
            sendCustomEvent(EVENT_FACEBOOK_PRESSED, hashMapOf("press" to press ))
        }
        fun trackLogInSucces(press: String){
            sendCustomEvent(EVENT_LOG_IN_SUCCESS, hashMapOf("press" to press ))
        }
        fun trackLogInError(press: String){
            sendCustomEvent(EVENT_LOG_IN_ERROR, hashMapOf("press" to press ))
        }

        fun trackLastNewsSeeMorePressed(press: String){
            sendCustomEvent(EVENT_LAST_NEWS_SEE_MORE_PRESSED, hashMapOf("press" to press ))
        }

        fun trackTestimonialsSeeMorePressed(press: String){
            sendCustomEvent(EVENT_TESTIMONIALS_SEE_MORE_PRESSED, hashMapOf("press" to press ))
        }

        fun trackSliderRetrieveSuccess(GET: String){
            sendCustomEvent(EVENT_SLIDER_RETRIEVE_SUCCESS, hashMapOf("GET" to GET ))
        }

        fun trackSliderRetrieveError(GET: String){
            sendCustomEvent(EVENT_SLIDER_RETRIEVE_ERROR, hashMapOf("GET" to GET ))
        }

        fun trackNewsRetrieveSuccess(GET: String){
            sendCustomEvent(EVENT_NEWS_RETRIEVE_SUCCESS, hashMapOf("GET" to GET ))
        }

        fun trackNewsRetrieveError(GET: String){
            sendCustomEvent(EVENT_NEWS_RETRIEVE_ERROR, hashMapOf("GET" to GET ))
        }

        fun trackTestimoniesRetrieveSuccess(GET: String){
            sendCustomEvent(EVENT_TESTIMONIALS_RETRIEVE_SUCCESS, hashMapOf("GET" to GET ))
        }

        fun trackTestimoniesRetrieveError(GET: String){
            sendCustomEvent(EVENT_TESTIMONIALS_RETRIEVE_ERROR, hashMapOf("GET" to GET ))
        }

        private fun sendCustomEvent(eventName: String, params: HashMap<String, String>){
            Firebase.analytics.logEvent(eventName){
                params.forEach { (key, value) ->
                    param(key, value)
                }

            }
        }
    }
}