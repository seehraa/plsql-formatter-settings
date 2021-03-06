package com.trivadis.plsql.formatter.sqlcl.tests;

import oracle.dbtools.raptor.newscriptrunner.CommandListener;
import oracle.dbtools.raptor.newscriptrunner.CommandRegistry;
import oracle.dbtools.raptor.newscriptrunner.SQLCommand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TvdFormatTest extends AbstractFormatTest {

    @Before
    public void register() {
        runScript("--register");
        byteArrayOutputStream.reset();
    }

    @Test
    public void duplicate_registration_using_mixed_case() {
        reset();
        final List<CommandListener> originalListeners = CommandRegistry.getListeners(null, ctx).get(
                SQLCommand.StmtSubType.G_S_FORALLSTMTS_STMTSUBTYPE);
        final String expected =
            """
            tvdformat registered as SQLcl command.
            """;

        // first registrations
        final String actual1 = runScript("--RegisteR");
        Assert.assertEquals(expected, actual1);
        final List<CommandListener> listeners1 = CommandRegistry.getListeners(null, ctx).get(
                SQLCommand.StmtSubType.G_S_FORALLSTMTS_STMTSUBTYPE);
        Assert.assertEquals(originalListeners.size() + 1, listeners1.size());

        // second registration
        byteArrayOutputStream.reset();
        final String actual2 = runScript("-R");
        Assert.assertEquals(expected, actual2);
        final List<CommandListener> listeners2 = CommandRegistry.getListeners(null, ctx).get(
                SQLCommand.StmtSubType.G_S_FORALLSTMTS_STMTSUBTYPE);
        Assert.assertEquals(originalListeners.size() + 1, listeners2.size());
    }

    @Test
    public void process_dir() {
        process_dir(RunType.TvdFormatCommand);
    }

    @Test
    public void process_pkb_only() {
        process_pkb_only(RunType.TvdFormatCommand);
    }

    @Test
    public void process_with_original_arbori() {
        process_with_original_arbori(RunType.TvdFormatCommand);
    }

    @Test
    public void process_with_default_arbori() {
        process_with_default_arbori(RunType.TvdFormatCommand);
    }

    @Test
    public void process_with_xml() {
        process_with_xml(RunType.TvdFormatCommand);
    }

    @Test
    public void process_with_default_xml_default_arbori() {
        process_with_default_xml_default_arbori(RunType.TvdFormatCommand);
    }

    @Test
    public void process_with_embedded_xml_default_arbori() {
        process_with_embedded_xml_default_arbori(RunType.TvdFormatCommand);
    }

    @Test
    public void process_markdown_only() {
        process_markdown_only(RunType.TvdFormatCommand);
    }

}
