package madvirus.spring.chap07.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller //��Ʈ�ѷ�Ŭ���� annotation
public class LocaleChangeController {
	private LocaleResolver localeResolver;
	
	@RequestMapping("/changeLanguage") //ȣ��Ʈ�κ��� ��û�̿��� �Ʒ� �޼ҵ�� ó��
	public String change(@RequestParam("lang") String language, HttpServletRequest request, HttpServletResponse response) {
		Locale locale = new Locale(language);
		localeResolver.setLocale(request, response, locale);
		return "redirect:/index.jsp"; //index.jsp�� �����̷�Ʈ      �������� �ּҺ�ȯ���� ���������̵� �����̷�Ʈ�� �ش� ������ �ּ����� �������̵���Ŵ
	}
	
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}
}
