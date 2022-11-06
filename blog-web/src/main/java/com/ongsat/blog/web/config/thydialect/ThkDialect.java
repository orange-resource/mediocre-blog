package com.ongsat.blog.web.config.thydialect;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

public class ThkDialect extends AbstractProcessorDialect {
    private static final String PREFIX = "thk";

    public ThkDialect() {
        super(PREFIX, PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String s) {
        final Set<IProcessor> processorSet = new HashSet<>();
        processorSet.add(new HtmlAttributeTagProcessor(PREFIX));
        return processorSet;
    }
}
