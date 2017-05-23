package io.github.brunovcosta.quillbill.controllers;

import java.util.Map;

public interface RestController {
	public String index(Map<String, String> map);
	public String show(Map<String, String> params);
	public String update(Map<String, String> params);
	public String create(Map<String, String> params);
	public String destroy(Map<String, String> params);
}
