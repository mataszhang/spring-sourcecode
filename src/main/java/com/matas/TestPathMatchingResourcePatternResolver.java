package com.matas;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.*;
import java.util.Arrays;

/**
 * Hello world!
 */
public class TestPathMatchingResourcePatternResolver {
    public static void main(String[] args) throws IOException {
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = pathResolver.getResources("classpath:*.txt");
        if (null != resources) {
            Arrays.stream(resources).forEach(resource -> {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                    String str = br.readLine();
                    System.out.println("read string from " + resource.getFile().getName() + " =>" + str);
                    System.out.println("url=>" +resource.getURL());
                    System.out.println("uri=>" +resource.getURI());
                    System.out.println("================");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
