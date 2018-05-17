package com.example.money.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("money")
public class MoneyAPIProperty {
	
	private String origemPermitidaString = "http://localhost:8000";
	private final Seguranca seguranca = new Seguranca();

	public Seguranca getSeguranca() {
		return seguranca;
	}

	public String getOrigemPermitidaString() {
		return origemPermitidaString;
	}

	public void setOrigemPermitidaString(String origemPermitidaString) {
		this.origemPermitidaString = origemPermitidaString;
	}

	public static class Seguranca{
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
	}
}
