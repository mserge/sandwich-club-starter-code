package com.udacity.sandwichclub.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


import static org.mockito.Mockito.*;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.SharedPreferences;

import com.udacity.sandwichclub.model.Sandwich;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JsonUtilsTest  {


    @Test
    public void testParseSandwichJson() {
        // Given a mocked Context injected into the object under test...
//        when(mMockContext.getString(R.string.hello_word))
//                .thenReturn(FAKE_STRING);
        String json = "{\"name\":{\"mainName\":\"test\",\"alsoKnownAs\":[\"test1\"]},\"placeOfOrigin\":\"\",\"description\":\"A ham and cheese " +
                "sandwich is a common type of sandwich. It is made by putting cheese and sliced ham" +
                "        between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables" +
                "        like lettuce, tomato, onion or pickle slices can also be included. Various kinds of" +
                "        mustard and mayonnaise are also common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"ingredients\":[\"Sliced " +
                "bread\",\"Cheese\",\"Ham\"]}";
        Sandwich myObjectUnderTest = JsonUtils.parseSandwichJson(json);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getMainName();

        // ...then the result should be the expected one.
        assertThat(result, is("test"));
//        assertThat(myObjectUnderTest.getAlsoKnownAs(), CoreMatchers.<List<String>>is(new ArrayList<String>()));
        assertThat(myObjectUnderTest.getAlsoKnownAs(), is(Arrays.asList("test1")));
        assertThat(myObjectUnderTest.getIngredients(), is(Arrays.asList("Sliced " +
                "bread","Cheese","Ham")));
        assertThat(myObjectUnderTest.getDescription(), containsString("mayonnaise"));
        assertThat(myObjectUnderTest.getImage(), containsString("https"));
        assertThat(myObjectUnderTest.getPlaceOfOrigin(), equalTo(""));

    }
}