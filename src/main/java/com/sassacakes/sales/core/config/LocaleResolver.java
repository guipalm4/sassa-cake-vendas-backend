//package com.sassacakes.sales.core.config;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Locale;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
//
//@Configuration
//public class LocaleResolver extends AcceptHeaderLocaleResolver {
//
//    private static final List<Locale> LOCALES = Collections.singletonList(new Locale("pt-BR"));
//
//    @Override
//    public Locale resolveLocale(HttpServletRequest request) {
//        String headerLang = request.getHeader("Accept-Language");
//        if (headerLang == null || headerLang.isEmpty()) {
//            return Locale.getDefault();
//        }
//        return Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
//    }
//}
