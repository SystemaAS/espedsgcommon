package no.systema.main.model;

import lombok.Data;

@Data
public class TomcatMetrics {
	private String date;
	private int logins;
	private int hour;
	private int totalSum;
}
