package com.resto.commonModel.queryUtil;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.function.Consumer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SearchQueryCriteriaConsumer<T> implements Consumer<SearchCriteria> {

	private Predicate predicate;
	private CriteriaBuilder builder;
	private Root<T> root;

	public SearchQueryCriteriaConsumer(Predicate predicate, CriteriaBuilder builder, Root<T> root) {
		this.predicate = predicate;
		this.builder = builder;
		this.root = root;
	}

	@Override
	public void accept(SearchCriteria param) {
		if (param.getOperation().equals(">")) {
			if(root.get(param.getKey()).getJavaType() == Timestamp.class) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = dateFormat.parse(param.getValue().toString());
					System.out.println(date);
					System.out.println(param.getValue());
					predicate = builder.and(predicate,
							builder.greaterThanOrEqualTo(root.get(param.getKey()).as(Date.class),date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else {
				predicate = builder.and(predicate,
						builder.greaterThan(root.get(param.getKey()), param.getValue().toString()));
			}
		} else if (param.getOperation().equals("<")) {
			if(root.get(param.getKey()).getJavaType() == Timestamp.class) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = dateFormat.parse(param.getValue().toString());
					System.out.println(date);
					System.out.println(param.getValue());
					predicate = builder.and(predicate,
							builder.lessThanOrEqualTo(root.get(param.getKey()).as(Date.class),date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else {
				predicate = builder.and(predicate, builder.lessThan(root.get(param.getKey()), param.getValue().toString()));
			}
		} else if (param.getOperation().equals("=")) {
			if(root.get(param.getKey()).getJavaType() == Timestamp.class) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date = dateFormat.parse(param.getValue().toString());
					System.out.println(date);
					System.out.println(param.getValue());
					predicate = builder.and(predicate,
							builder.equal(root.get(param.getKey()).as(Date.class),date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			else {
				predicate = builder.and(predicate, builder.equal(root.get(param.getKey()), param.getValue().toString()));
			}
		} else if (param.getOperation().equals(":")) {
			if(root.get(param.getKey()).getJavaType() == String.class) {
				predicate = builder.and(predicate,
						builder.like(root.get(param.getKey()), "%" + param.getValue().toString() + "%"));
			}
		}
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}

}
