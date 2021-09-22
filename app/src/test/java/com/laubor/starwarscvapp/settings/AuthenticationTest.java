package com.laubor.starwarscvapp.settings;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import okhttp3.OkHttpClient;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class AuthenticationTest {

    private Authentication authenticationUnderTest;

    @Before
    public void setUp() {
        authenticationUnderTest = new Authentication("baseAPIURL");
    }

    @Test
    public void testGetClient() throws Exception {
        // Setup
        final Context context = null;

        // Run the test
        final OkHttpClient result = authenticationUnderTest.getClient(context);

        // Verify the results
    }

    @Test(expected = CertificateException.class)
    public void testGetClient_ThrowsCertificateException() throws Exception {
        // Setup
        final Context context = null;

        // Run the test
        authenticationUnderTest.getClient(context);
    }

    @Test(expected = NoSuchAlgorithmException.class)
    public void testGetClient_ThrowsNoSuchAlgorithmException() throws Exception {
        // Setup
        final Context context = null;

        // Run the test
        authenticationUnderTest.getClient(context);
    }

    @Test(expected = KeyStoreException.class)
    public void testGetClient_ThrowsKeyStoreException() throws Exception {
        // Setup
        final Context context = null;

        // Run the test
        authenticationUnderTest.getClient(context);
    }

    @Test(expected = KeyManagementException.class)
    public void testGetClient_ThrowsKeyManagementException() throws Exception {
        // Setup
        final Context context = null;

        // Run the test
        authenticationUnderTest.getClient(context);
    }

    @Test(expected = IOException.class)
    public void testGetClient_ThrowsIOException() throws Exception {
        // Setup
        final Context context = null;

        // Run the test
        authenticationUnderTest.getClient(context);
    }

    @Test
    public void testGetAuthentication() throws Exception {
        // Run the test
        final Authentication result = Authentication.getAuthentication();
        assertEquals("result", result.getBaseAPIURL());
        final Context context = null;
        assertEquals(new OkHttpClient(), result.getClient(context));
    }
}
