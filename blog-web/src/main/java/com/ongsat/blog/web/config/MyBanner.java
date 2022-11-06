package com.ongsat.blog.web.config;

import com.ongsat.blog.api.constant.BlogVersionConstant;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class MyBanner implements Banner {

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        out.println("                _ _                           _     _\n" +
                "               | (_)                         | |   | |\n" +
                " ____   ____ _ | |_  ___   ____  ____ ____   | | _ | | ___   ____\n" +
                "|    \\ / _  ) || | |/ _ \\ / ___)/ ___) _  )  | || \\| |/ _ \\ / _  |\n" +
                "| | | ( (/ ( (_| | | |_| ( (___| |  ( (/ /   | |_) ) | |_| ( ( | |\n" +
                "|_|_|_|\\____)____|_|\\___/ \\____)_|   \\____)  |____/|_|\\___/ \\_|| |\n" +
                "                                                           (_____|");
        out.println("blog: " + BlogVersionConstant.CURRENT_VERSION);
        out.println("spring boot: " + SpringBootVersion.getVersion());
    }
}
