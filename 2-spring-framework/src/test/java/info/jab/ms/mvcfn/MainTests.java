package info.jab.ms.mvcfn;

import org.junit.jupiter.api.Test;

import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.StaticWebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MainTests {
	MockMvc mockMvc = initMockMvc();

	MockMvc initMockMvc() {
		StaticWebApplicationContext applicationContext = Main.applicationContext(Main.routes());
		applicationContext.setServletContext(new MockServletContext());
		applicationContext.refresh();
		// https://github.com/spring-projects/spring-framework/issues/30477
		return MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}

	@Test
	void hello() throws Exception {
		this.mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello world"));
	}

}