package com.example.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sanitizing")
public class SanitizingController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final PolicyFactory policy = new HtmlPolicyBuilder().toFactory();

    @RequestMapping
    public String verifyRequest(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOG.info("Checking {} attributes from request", parameterMap.size());
        
        for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
            // Get the raw version of the parameter name
            String parameterName = parameter.getKey();
            // Calculate a sanitized version of the parameter name
            String sanitizedName = policy.sanitize(parameterName);
            // If the raw version is not sanitary, redirect to the error view
            if (Objects.equals(parameterName, sanitizedName) == false) return "error";
            
            String[] parameterValueArray = parameter.getValue();
            // For each raw version of the parameter value
            for (String parameterValue : parameterValueArray) {
                // Calculate a sanitized version of the parameter value
                String sanitizedValue = policy.sanitize(parameterValue);
                // If the raw version is not sanitary, redirect to the error view
                if (Objects.equals(parameterValue, sanitizedValue) == false) return "error";
            }
        }

        // Redirect to the sanitizing view
        return "sanitizing";
    }
}
