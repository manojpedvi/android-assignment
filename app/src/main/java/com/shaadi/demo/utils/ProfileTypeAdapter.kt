package com.shaadi.demo.utils

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.shaadi.demo.model.Profile
import java.lang.reflect.Type
import kotlin.jvm.Throws


class ProfileTypeAdapter  :
    JsonDeserializer<Profile> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        je: JsonElement,
        type: Type?,
        jdc: JsonDeserializationContext?
    ): Profile {
        val nameObj = je.asJsonObject.get("name").asJsonObject
        val pictureObj = je.asJsonObject.get("picture").asJsonObject
        val dobObj = je.asJsonObject.get("dob").asJsonObject
        val locationObj = je.asJsonObject.get("location").asJsonObject
        val fullName = "${nameObj.get("title").asString} ${nameObj.get("first").asString} ${nameObj.get("last").asString}"
        return Profile(
            id = 0,
            thumbnailUrl = pictureObj.get("medium").asString,
            name = fullName,
            age = dobObj.get("age").asInt,
            city = locationObj.get("city").asString,
            state = locationObj.get("state").asString,
            country = locationObj.get("country").asString
        );
    }
}