

/* --------------------------Usercode Section------------------------ */
package src;
import java_cup.runtime.*;

%%

/* -----------------Options and Declarations Section----------------- */

/*
   The name of the class JFlex will create will be Lexer.
   Will write the code to the file Lexer.java.
*/
%class Lexer

/*
  The current line number can be accessed with the variable yyline
  and the current column number with the variable yycolumn.
*/
%line
%column

/*
   Will switch to a CUP compatibility mode to interface with a CUP
   generated parser.
*/
%cup

/*
  Declarations

  Code between %{ and %}, both of which must be at the beginning of a
  line, will be copied letter to letter into the lexer class source.
  Here you declare member variables and functions that are used inside
  scanner actions.
*/
%{
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }

    private void debug(String type){
        System.out.print("<" + type + ">");
    }

    private void debug(String type, String text){
        System.out.print("<" + type + "," + text + ">");
    }
%}


/*
  Macro Declarations

  These declarations are regular expressions that will be used latter
  in the Lexical Rules Section.
*/

/* A line terminator is a \r (carriage return), \n (line feed), or \r\n. */

LineTerminator = \r|\n|\r\n

/* White space is a line terminator, space, tab, or line feed. */

WhiteSpace     = {LineTerminator} | [ \t\f]

// helpers
dot = "."

// Data types
int = 0 | [1-9][0-9]*
float = [0-9]+{dot}[0-9]+
char   = \"[a-zA-Z0-9!|@|#|$|%|\^|&|*|(|)|\' ]*\"
id = [A-Za-z_][A-Za-z_0-9]*

boolean = true|false

%%
/* ------------------------Lexical Rules Section---------------------- */

/*
   This section contains regular expressions and actions, i.e. Java
   code, that will be executed when the scanner matches the associated
   regular expression. */


    "+"        { debug("PLUS"); return symbol(sym.PLUS); }
    "-"        { debug("MINUS"); return symbol(sym.MINUS); }
    "*"        { debug("TIMES"); return symbol(sym.TIMES); }
    "/"        { debug("DIVIDE"); return symbol(sym.DIVIDE); }
    "%"        { debug("MOD"); return symbol(sym.MOD); }
    "{"        { debug("LCURL"); return symbol(sym.LCURL); }
    "}"        { debug("RCURL"); return symbol(sym.RCURL); }
    "("        { debug("LPAR"); return symbol(sym.LPAR); }
    ")"        { debug("RPAR"); return symbol(sym.RPAR); }
    "="        { debug("EQ"); return symbol(sym.EQ); }
    ";"        { debug("SEMI"); return symbol(sym.SEMI); }
    ","        { debug("COMMA"); return symbol(sym.COMMA); }

    "publish"  { debug("publish"); return symbol (sym.PUBLISH);}
    "if"       { debug("if"); return symbol (sym.IF);}
    "else"     { debug("else"); return symbol (sym.ELSE);}
    "while"     { debug("while"); return symbol (sym.WHILE);}
    "and"     { debug("and"); return symbol (sym.AND);}
    "or"     { debug("or"); return symbol (sym.OR);}
    "<="     { debug("lte"); return symbol (sym.LTE);}
    ">="     { debug("gte"); return symbol (sym.GTE);}
    ">"     { debug("gt"); return symbol (sym.GT);}
    "<"     { debug("lt"); return symbol (sym.LT);}
    "is"     { debug("equal"); return symbol (sym.EQUAL);}
    "is not"     { debug("notequal"); return symbol (sym.NOTEQUAL);}
    "=="     { debug("equal"); return symbol (sym.EQUAL);}
    "!="     { debug("notequal"); return symbol (sym.NOTEQUAL);}

    "bool"     { debug("BOOL"); return symbol (sym.BOOL);}
    "int"      { debug("INT"); return symbol (sym.INT);}
    "float"    { debug("FLOAT"); return symbol (sym.FLOAT);}
    "char"     { debug("STRING"); return symbol (sym.CHAR);}

    //"true"     { debug("true"); return symbol (sym.TRUE);}


    {int}      { debug("INTEGER_LITERAL", yytext()); return symbol(sym.INTEGER_LITERAL, new Integer(yytext())); }
    {float}    { debug("FLOATING_POINT_LITERAL", yytext()); return symbol(sym.FLOATING_LITERAL, new Float(yytext()));}
    {boolean}  { debug("BOOLEAN_LITERAL", yytext()); return symbol(sym.BOOLEAN_LITERAL, new Boolean(yytext()));}
    {char}     { debug("CHAR_LITERAL", yytext()); return symbol(sym.CHAR_LITERAL, new String (yytext().substring(1,yylength()-1)));}

    {id}       {
                debug("id", yytext());
                return symbol(sym.ID, yytext());
               }

    

    /* Don't do anything if whitespace is found */

    {WhiteSpace}       { /* just skip what was found, do nothing */ }


/* No token was found for the input so through an error.  Print out an
   Illegal character message with the illegal character that was found. */

[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
