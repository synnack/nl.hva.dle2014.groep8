package nl.hva.web.dle2014.groep8.database;

/**
 * Defines a filter to perform queries with
 * 
 * @author Wilco Baan Hofman
 *
 */
public class Filter {

	public enum Operator {
		EQUALS,
		LESS,
		LESS_OR_EQUAL,
		GREATER,
		GREATER_OR_EQUAL,
		LIKE,
	}
	
	public String field;
	public Operator operator;
	public String value;
	
	Filter(String field, Operator operator, String value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
}
