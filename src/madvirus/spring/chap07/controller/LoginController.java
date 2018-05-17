package madvirus.spring.chap07.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //컨트롤러 클래스 
@RequestMapping("/login/login.do") //경로 요청이 오면 아래 클래스로 처리함
public class LoginController {
	private Authenticator authenticator;
	
	@ModelAttribute("login") //뷰에서 가져다쓸때 이름을 login으로 설정하고 loginCommand커맨드객체 생성.
	public LoginCommand formBacking() {
		return new LoginCommand();
	}
	
	@RequestMapping(method=RequestMethod.GET) //get메소드로 요청이오면 아래 메소드로 처리함.
	public String form() {
		return "loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST) //post메소드로 요청이오면 아래 메소드로 처리함.
	public String submit(@ModelAttribute("login")LoginCommand loginCommand, BindingResult result) {
		new LoginCommandValidator().validate(loginCommand, result);
		if(result.hasErrors()) {
			return "loginForm";
		}try {
			authenticator.authenticate(loginCommand.getId(), loginCommand.getPassword());
			return "loginSuccess";
		}catch(AuthenticationException ex) {
			result.reject("invalidIdOrPassword", new Object[] {loginCommand.getId()}, null);
			return "loginForm";
		}
	}
	
	@ModelAttribute("loginTypes")
	protected List<String> referenceData() throws Exception{
		List<String> loginTypes=new ArrayList<String>();
		loginTypes.add("일반 회원");
		loginTypes.add("기업 회원");
		loginTypes.add("헤드헌터 회원");
		return loginTypes;
	}
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator=authenticator;
	}

}
