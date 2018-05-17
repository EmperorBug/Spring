package madvirus.spring.chap07.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller //컨트롤러클래스 annotation
public class LocaleChangeController {
	private LocaleResolver localeResolver;
	
	@RequestMapping("/changeLanguage") //호스트로부터 요청이오면 아래 메소드로 처리
	public String change(@RequestParam("lang") String language, HttpServletRequest request, HttpServletResponse response) {
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		return "redirect:/index.jsp"; //index.jsp로 리다이렉트      포워딩은 주소변환없이 페이지만이동 리다이렉트는 해당 페이지 주소포함 페이지이동시킴
	}
	
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
}
