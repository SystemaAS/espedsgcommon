package no.systema.main.model;

import lombok.Data;

@Data
public class TomcatMetrics {
	private String date = "9999-01-01 00:00";
	private int logins = 0;
	private int hour = 0;
	private int totalSum = 0;
}
