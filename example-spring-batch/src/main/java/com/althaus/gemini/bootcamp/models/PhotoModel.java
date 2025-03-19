package com.althaus.gemini.bootcamp.models;

import lombok.Data;

@Data
public class PhotoModel {
	private String id, author, url, download_url;
	private int width, height;
}
