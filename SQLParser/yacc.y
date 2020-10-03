%{
#include <stdio.h>

void yyerror (const char *str) {
	fprintf(stderr, "error: %s\n", str);
}

int yywrap() {
	return 1;
}

main() {
	yyparse();
}

%}

%%

%token SELECT FROM IDENTIFIER WHERE AND OR;

line: select items using '\n' { printf("Syntax Correct\n"); };

select: SELECT;

items: '*' | identifiers;

identifiers: IDENTIFIER | IDENTIFIER ',' identifiers;

using: FROM IDENTIFIER WHERE condition| FROM IDENTIFIER;

condition: IDENTIFIER '=' IDENTIFIER | IDENTIFIER '=' IDENTIFIER AND condition | IDENTIFIER '=' IDENTIFIER OR condition;

%%