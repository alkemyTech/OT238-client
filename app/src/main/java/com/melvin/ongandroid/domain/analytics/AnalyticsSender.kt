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