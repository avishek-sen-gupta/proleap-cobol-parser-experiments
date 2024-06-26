package io.proleap.cobol.asg.procedure.accept;

import io.proleap.cobol.CobolTestBase;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.FigurativeConstant;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call.CallType;
import io.proleap.cobol.asg.metamodel.procedure.Paragraph;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.accept.*;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveStatement;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveToStatement;
import io.proleap.cobol.asg.metamodel.valuestmt.LiteralValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.asg.params.impl.CobolParserParamsImpl;
import io.proleap.cobol.asg.runner.impl.CobolParserRunnerImpl;
import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IdmsStatementTest extends CobolTestBase {

    @Test
    public void testCopyIdms() throws Exception {
//		final File inputFile = new File("/Users/asgupta/code/mbrdi-proleap/from-github/proleap-cobol-parser-experiments/src/test/resources/io/proleap/cobol/asg/procedure/accept/mbrdi-sample-code/preprocessed/program V7596428.txt");
//		final File inputFile = new File("/Users/asgupta/code/mbrdi-proleap/from-github/proleap-cobol-parser-experiments/src/test/resources/io/proleap/cobol/asg/procedure/accept/mbrdi-poc/V7523438.txt");
        final File inputFile = new File("/Users/asgupta/code/mbrdi-proleap/from-github/proleap-cobol-parser-experiments/src/test/resources/io/proleap/cobol/asg/procedure/accept/mbrdi-poc/V751C931.txt");
//		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/accept/IdmsStatement.cbl");
//		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/accept/Copybook V75CW880.txt");
        CobolParserRunnerImpl parserRunner = new CobolParserRunnerImpl();
        CobolParserParams params = parserRunner.createDefaultParams(CobolSourceFormatEnum.VARIABLE, inputFile);

        params.setFormat(CobolSourceFormatEnum.VARIABLE);
        params.setIgnoreSyntaxErrors(true);
        final Program program = parserRunner.analyzeFile(inputFile, params);

//        System.out.println(program.getCompilationUnits());
        for (CompilationUnit compilationUnit : program.getCompilationUnits()) {
            System.out.println(compilationUnit.getName());
        }
//		final CompilationUnit compilationUnit = program.getCompilationUnit("V7523438");
        final CompilationUnit compilationUnit = program.getCompilationUnit("V751C931");
        List<ProgramUnit> programUnits = compilationUnit.getProgramUnits();
        for (ProgramUnit pu : programUnits) {
            System.out.println("Proc division = " + pu.getProcedureDivision());
        }

        final ProgramUnit programUnit = compilationUnit.getProgramUnit();
        final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
        System.out.println(procedureDivision);
        Paragraph firstParagraph = procedureDivision.getParagraphs().get(0);
        System.out.println("PARAGRAPH NAME IS " + firstParagraph.getParagraphName());
        System.out.println(firstParagraph.getStatements().get(0).getStatementType());
        System.out.println(((MoveStatement)firstParagraph.getStatements().get(0)).getMoveToStatement().getReceivingAreaCalls());
        ValueStmt sendingAreaValueStmt = ((MoveStatement) firstParagraph.getStatements().get(0)).getMoveToStatement().getSendingArea().getSendingAreaValueStmt();
        LiteralValueStmt stmt = (LiteralValueStmt) sendingAreaValueStmt;
        System.out.println(((FigurativeConstant)stmt.getLiteral().getValue()).getFigurativeConstantType());
        assertEquals(0, procedureDivision.getParagraphs().size());
        assertEquals(4, procedureDivision.getStatements().size());

        {
            final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(0);
            assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
            assertEquals(AcceptStatement.AcceptType.DATE, acceptStatement.getAcceptType());

            assertNotNull(acceptStatement.getAcceptCall());
            assertEquals(CallType.UNDEFINED_CALL, acceptStatement.getAcceptCall().getCallType());

            {
                final AcceptFromDateStatement acceptFromDateStatement = acceptStatement.getAcceptFromDateStatement();
                assertNotNull(acceptFromDateStatement);
                assertEquals(AcceptFromDateStatement.DateType.TODAYS_NAME, acceptFromDateStatement.getDateType());
            }
        }

        {
            final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(1);
            assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
            assertEquals(AcceptStatement.AcceptType.MNEMONIC, acceptStatement.getAcceptType());

            assertNotNull(acceptStatement.getAcceptCall());
            assertEquals(CallType.UNDEFINED_CALL, acceptStatement.getAcceptCall().getCallType());

            {
                final AcceptFromMnemonicStatement acceptFromMnemonicStatement = acceptStatement
                        .getAcceptFromMnemonicStatement();
                assertNotNull(acceptFromMnemonicStatement);
                assertNotNull(acceptFromMnemonicStatement.getMnemonicCall());
                assertEquals(CallType.MNEMONIC_CALL, acceptFromMnemonicStatement.getMnemonicCall().getCallType());
            }
        }

        {
            final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(2);
            assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
            assertEquals(AcceptStatement.AcceptType.MESSAGE_COUNT, acceptStatement.getAcceptType());

            assertNotNull(acceptStatement.getAcceptCall());
            assertEquals(CallType.UNDEFINED_CALL, acceptStatement.getAcceptCall().getCallType());

            {
                final AcceptMessageCountStatement acceptMessageCountStatement = acceptStatement
                        .getAcceptMessageCountStatement();
                assertNotNull(acceptMessageCountStatement);
            }
        }

        {
            final AcceptStatement acceptStatement = (AcceptStatement) procedureDivision.getStatements().get(3);
            assertEquals(StatementTypeEnum.ACCEPT, acceptStatement.getStatementType());
            assertEquals(AcceptStatement.AcceptType.FROM_ESCAPE_KEY, acceptStatement.getAcceptType());

            assertNotNull(acceptStatement.getAcceptCall());

            {
                final AcceptFromEscapeKeyStatement acceptFromEscapeKeyStatement = acceptStatement
                        .getAcceptFromEscapeKeyStatement();
                assertNotNull(acceptFromEscapeKeyStatement);
            }
        }
    }
}
