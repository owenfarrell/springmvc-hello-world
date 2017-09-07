package com.example;

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

    @RequestMapping()
    public String verifyRequest(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        LOG.info("Checking {} attributes from request", parameterMap.size());
        
        for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
            String parameterName = parameter.getKey();
            String sanitizedName = policy.sanitize(parameterName);
            if (Objects.equals(parameterName, sanitizedName) == false) return "error";
            
            String[] parameterValueArray = parameter.getValue();
            for (String parameterValue : parameterValueArray) {
                String sanitizedValue = policy.sanitize(parameterValue);
                if (Objects.equals(parameterValue, sanitizedValue) == false) return "error";
            }
        }

        return "sanitizing";
    }
}
