package com.example.citrixcommute;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest {

    private static final String SIGNUP_REQUEST_URL = "https://citrixcommute.000webhostapp.com/register.php";
    private Map<String, String> params;

    public SignupRequest(String name, String password, String emailid, String homeaddress, Response.Listener<String> listener) {
        super(Method.POST, SIGNUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("password", password);
        params.put("emailid", emailid);
        params.put("homeaddress", homeaddress);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
