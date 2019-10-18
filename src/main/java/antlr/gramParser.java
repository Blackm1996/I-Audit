// Generated from D:/mohye/IdeaProjects/I-Audit/src/main/java/antlr\gram.g4 by ANTLR 4.7.2
package antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class gramParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FOR_ALL=1, REJECT_ALL_BUT=2, ACCEPT_ALL_BUT=3, ACCEPT=4, REJECT=5, AND=6, 
		OR=7, NOT=8, TRUE=9, FALSE=10, GT=11, GE=12, LT=13, LE=14, DIF=15, EQ=16, 
		LPAREN=17, RPAREN=18, LBRACE=19, RBRACE=20, Old=21, New=22, Dot=23, STRING=24, 
		DECIMAL=25, IDENTIFIER=26, WS=27;
	public static final int
		RULE_parse = 0, RULE_for_all = 1, RULE_accept = 2, RULE_reject = 3, RULE_accept_all_but = 4, 
		RULE_reject_all_but = 5, RULE_expression = 6, RULE_comparator = 7, RULE_binary = 8, 
		RULE_bool = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "for_all", "accept", "reject", "accept_all_but", "reject_all_but", 
			"expression", "comparator", "binary", "bool"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'For_All'", "'Reject_All_But'", "'Accept_All_But'", "'Accept'", 
			"'Reject'", "'AND'", "'OR'", "'!'", "'TRUE'", "'FALSE'", "'>'", "'>='", 
			"'<'", "'<='", "'!='", "'=='", "'('", "')'", "'{'", "'}'", "'Old'", "'New'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "FOR_ALL", "REJECT_ALL_BUT", "ACCEPT_ALL_BUT", "ACCEPT", "REJECT", 
			"AND", "OR", "NOT", "TRUE", "FALSE", "GT", "GE", "LT", "LE", "DIF", "EQ", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "Old", "New", "Dot", "STRING", 
			"DECIMAL", "IDENTIFIER", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "gram.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public gramParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(gramParser.EOF, 0); }
		public For_allContext for_all() {
			return getRuleContext(For_allContext.class,0);
		}
		public AcceptContext accept() {
			return getRuleContext(AcceptContext.class,0);
		}
		public RejectContext reject() {
			return getRuleContext(RejectContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR_ALL:
				{
				setState(20);
				for_all();
				}
				break;
			case ACCEPT:
				{
				setState(21);
				accept();
				}
				break;
			case REJECT:
				{
				setState(22);
				reject();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(25);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_allContext extends ParserRuleContext {
		public Token sequence;
		public AcceptContext sa;
		public RejectContext sr;
		public Accept_all_butContext acall;
		public Reject_all_butContext reall;
		public TerminalNode FOR_ALL() { return getToken(gramParser.FOR_ALL, 0); }
		public TerminalNode LPAREN() { return getToken(gramParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(gramParser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(gramParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(gramParser.RBRACE, 0); }
		public TerminalNode STRING() { return getToken(gramParser.STRING, 0); }
		public AcceptContext accept() {
			return getRuleContext(AcceptContext.class,0);
		}
		public RejectContext reject() {
			return getRuleContext(RejectContext.class,0);
		}
		public List<Accept_all_butContext> accept_all_but() {
			return getRuleContexts(Accept_all_butContext.class);
		}
		public Accept_all_butContext accept_all_but(int i) {
			return getRuleContext(Accept_all_butContext.class,i);
		}
		public List<Reject_all_butContext> reject_all_but() {
			return getRuleContexts(Reject_all_butContext.class);
		}
		public Reject_all_butContext reject_all_but(int i) {
			return getRuleContext(Reject_all_butContext.class,i);
		}
		public For_allContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_all; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterFor_all(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitFor_all(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitFor_all(this);
			else return visitor.visitChildren(this);
		}
	}

	public final For_allContext for_all() throws RecognitionException {
		For_allContext _localctx = new For_allContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_for_all);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			match(FOR_ALL);
			setState(28);
			match(LPAREN);
			setState(29);
			((For_allContext)_localctx).sequence = match(STRING);
			setState(30);
			match(RPAREN);
			setState(31);
			match(LBRACE);
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACCEPT:
				{
				setState(32);
				((For_allContext)_localctx).sa = accept();
				}
				break;
			case REJECT:
				{
				setState(33);
				((For_allContext)_localctx).sr = reject();
				}
				break;
			case REJECT_ALL_BUT:
			case ACCEPT_ALL_BUT:
			case RBRACE:
				break;
			default:
				break;
			}
			setState(40);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==REJECT_ALL_BUT || _la==ACCEPT_ALL_BUT) {
				{
				setState(38);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ACCEPT_ALL_BUT:
					{
					setState(36);
					((For_allContext)_localctx).acall = accept_all_but();
					}
					break;
				case REJECT_ALL_BUT:
					{
					setState(37);
					((For_allContext)_localctx).reall = reject_all_but();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(42);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(43);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AcceptContext extends ParserRuleContext {
		public TerminalNode ACCEPT() { return getToken(gramParser.ACCEPT, 0); }
		public TerminalNode LBRACE() { return getToken(gramParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(gramParser.RBRACE, 0); }
		public AcceptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accept; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterAccept(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitAccept(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitAccept(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcceptContext accept() throws RecognitionException {
		AcceptContext _localctx = new AcceptContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_accept);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(ACCEPT);
			setState(46);
			match(LBRACE);
			setState(47);
			expression(0);
			setState(48);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RejectContext extends ParserRuleContext {
		public TerminalNode REJECT() { return getToken(gramParser.REJECT, 0); }
		public TerminalNode LBRACE() { return getToken(gramParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(gramParser.RBRACE, 0); }
		public RejectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reject; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterReject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitReject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitReject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RejectContext reject() throws RecognitionException {
		RejectContext _localctx = new RejectContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_reject);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(REJECT);
			setState(51);
			match(LBRACE);
			setState(52);
			expression(0);
			setState(53);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Accept_all_butContext extends ParserRuleContext {
		public TerminalNode ACCEPT_ALL_BUT() { return getToken(gramParser.ACCEPT_ALL_BUT, 0); }
		public TerminalNode LBRACE() { return getToken(gramParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(gramParser.RBRACE, 0); }
		public Accept_all_butContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accept_all_but; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterAccept_all_but(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitAccept_all_but(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitAccept_all_but(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Accept_all_butContext accept_all_but() throws RecognitionException {
		Accept_all_butContext _localctx = new Accept_all_butContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_accept_all_but);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(ACCEPT_ALL_BUT);
			setState(56);
			match(LBRACE);
			setState(57);
			expression(0);
			setState(58);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Reject_all_butContext extends ParserRuleContext {
		public TerminalNode REJECT_ALL_BUT() { return getToken(gramParser.REJECT_ALL_BUT, 0); }
		public TerminalNode LBRACE() { return getToken(gramParser.LBRACE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACE() { return getToken(gramParser.RBRACE, 0); }
		public Reject_all_butContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reject_all_but; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterReject_all_but(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitReject_all_but(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitReject_all_but(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Reject_all_butContext reject_all_but() throws RecognitionException {
		Reject_all_butContext _localctx = new Reject_all_butContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_reject_all_but);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(REJECT_ALL_BUT);
			setState(61);
			match(LBRACE);
			setState(62);
			expression(0);
			setState(63);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinaryExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public BinaryContext op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryContext binary() {
			return getRuleContext(BinaryContext.class,0);
		}
		public BinaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterBinaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitBinaryExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitBinaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DecimalExpressionContext extends ExpressionContext {
		public TerminalNode DECIMAL() { return getToken(gramParser.DECIMAL, 0); }
		public DecimalExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterDecimalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitDecimalExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitDecimalExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringExpressionContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(gramParser.STRING, 0); }
		public StringExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpressionContext extends ExpressionContext {
		public BoolContext bool() {
			return getRuleContext(BoolContext.class,0);
		}
		public BoolExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterBoolExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitBoolExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitBoolExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentifierExpressionContext extends ExpressionContext {
		public Token old;
		public Token neww;
		public TerminalNode Dot() { return getToken(gramParser.Dot, 0); }
		public TerminalNode IDENTIFIER() { return getToken(gramParser.IDENTIFIER, 0); }
		public TerminalNode Old() { return getToken(gramParser.Old, 0); }
		public TerminalNode New() { return getToken(gramParser.New, 0); }
		public IdentifierExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterIdentifierExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitIdentifierExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitIdentifierExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExpressionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(gramParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(gramParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(gramParser.RPAREN, 0); }
		public ParenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitParenExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitParenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComparatorExpressionContext extends ExpressionContext {
		public ExpressionContext left;
		public ComparatorContext op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public ComparatorExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterComparatorExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitComparatorExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitComparatorExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				_localctx = new ParenExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(66);
				match(LPAREN);
				setState(67);
				expression(0);
				setState(68);
				match(RPAREN);
				}
				break;
			case NOT:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(70);
				match(NOT);
				setState(71);
				expression(7);
				}
				break;
			case TRUE:
			case FALSE:
				{
				_localctx = new BoolExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(72);
				bool();
				}
				break;
			case STRING:
				{
				_localctx = new StringExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(STRING);
				}
				break;
			case Old:
			case New:
				{
				_localctx = new IdentifierExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(76);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case Old:
					{
					setState(74);
					((IdentifierExpressionContext)_localctx).old = match(Old);
					}
					break;
				case New:
					{
					setState(75);
					((IdentifierExpressionContext)_localctx).neww = match(New);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(78);
				match(Dot);
				setState(79);
				match(IDENTIFIER);
				}
				break;
			case DECIMAL:
				{
				_localctx = new DecimalExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				match(DECIMAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(93);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(91);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						_localctx = new ComparatorExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((ComparatorExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(83);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(84);
						((ComparatorExpressionContext)_localctx).op = comparator();
						setState(85);
						((ComparatorExpressionContext)_localctx).right = expression(7);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExpressionContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExpressionContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(87);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(88);
						((BinaryExpressionContext)_localctx).op = binary();
						setState(89);
						((BinaryExpressionContext)_localctx).right = expression(6);
						}
						break;
					}
					} 
				}
				setState(95);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode GT() { return getToken(gramParser.GT, 0); }
		public TerminalNode GE() { return getToken(gramParser.GE, 0); }
		public TerminalNode LT() { return getToken(gramParser.LT, 0); }
		public TerminalNode LE() { return getToken(gramParser.LE, 0); }
		public TerminalNode DIF() { return getToken(gramParser.DIF, 0); }
		public TerminalNode EQ() { return getToken(gramParser.EQ, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << GE) | (1L << LT) | (1L << LE) | (1L << DIF) | (1L << EQ))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BinaryContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(gramParser.AND, 0); }
		public TerminalNode OR() { return getToken(gramParser.OR, 0); }
		public BinaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BinaryContext binary() throws RecognitionException {
		BinaryContext _localctx = new BinaryContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_binary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !(_la==AND || _la==OR) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BoolContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(gramParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(gramParser.FALSE, 0); }
		public BoolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof gramListener ) ((gramListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof gramVisitor ) return ((gramVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolContext bool() throws RecognitionException {
		BoolContext _localctx = new BoolContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bool);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35i\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\5\2\32\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3%\n\3\3\3"+
		"\3\3\7\3)\n\3\f\3\16\3,\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\bO\n\b\3\b\3\b\3\b\5\bT\n\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\b^\n\b\f\b\16\ba\13\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\2"+
		"\3\16\f\2\4\6\b\n\f\16\20\22\24\2\5\3\2\r\22\3\2\b\t\3\2\13\f\2l\2\31"+
		"\3\2\2\2\4\35\3\2\2\2\6/\3\2\2\2\b\64\3\2\2\2\n9\3\2\2\2\f>\3\2\2\2\16"+
		"S\3\2\2\2\20b\3\2\2\2\22d\3\2\2\2\24f\3\2\2\2\26\32\5\4\3\2\27\32\5\6"+
		"\4\2\30\32\5\b\5\2\31\26\3\2\2\2\31\27\3\2\2\2\31\30\3\2\2\2\32\33\3\2"+
		"\2\2\33\34\7\2\2\3\34\3\3\2\2\2\35\36\7\3\2\2\36\37\7\23\2\2\37 \7\32"+
		"\2\2 !\7\24\2\2!$\7\25\2\2\"%\5\6\4\2#%\5\b\5\2$\"\3\2\2\2$#\3\2\2\2$"+
		"%\3\2\2\2%*\3\2\2\2&)\5\n\6\2\')\5\f\7\2(&\3\2\2\2(\'\3\2\2\2),\3\2\2"+
		"\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,*\3\2\2\2-.\7\26\2\2.\5\3\2\2\2/\60\7"+
		"\6\2\2\60\61\7\25\2\2\61\62\5\16\b\2\62\63\7\26\2\2\63\7\3\2\2\2\64\65"+
		"\7\7\2\2\65\66\7\25\2\2\66\67\5\16\b\2\678\7\26\2\28\t\3\2\2\29:\7\5\2"+
		"\2:;\7\25\2\2;<\5\16\b\2<=\7\26\2\2=\13\3\2\2\2>?\7\4\2\2?@\7\25\2\2@"+
		"A\5\16\b\2AB\7\26\2\2B\r\3\2\2\2CD\b\b\1\2DE\7\23\2\2EF\5\16\b\2FG\7\24"+
		"\2\2GT\3\2\2\2HI\7\n\2\2IT\5\16\b\tJT\5\24\13\2KT\7\32\2\2LO\7\27\2\2"+
		"MO\7\30\2\2NL\3\2\2\2NM\3\2\2\2OP\3\2\2\2PQ\7\31\2\2QT\7\34\2\2RT\7\33"+
		"\2\2SC\3\2\2\2SH\3\2\2\2SJ\3\2\2\2SK\3\2\2\2SN\3\2\2\2SR\3\2\2\2T_\3\2"+
		"\2\2UV\f\b\2\2VW\5\20\t\2WX\5\16\b\tX^\3\2\2\2YZ\f\7\2\2Z[\5\22\n\2[\\"+
		"\5\16\b\b\\^\3\2\2\2]U\3\2\2\2]Y\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2"+
		"`\17\3\2\2\2a_\3\2\2\2bc\t\2\2\2c\21\3\2\2\2de\t\3\2\2e\23\3\2\2\2fg\t"+
		"\4\2\2g\25\3\2\2\2\n\31$(*NS]_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}