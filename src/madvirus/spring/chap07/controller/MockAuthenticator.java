package madvirus.spring.chap07.controller;

public class MockAuthenticator implements Authenticator{
	@Override
	public void authenticate(String id, String password) {
		if(!id.equals("madvirus")) { //id가 madvirus가 아니면
			throw new AuthenticationException("invalid id="+id); //예외처리시킴
		}
	}

}
