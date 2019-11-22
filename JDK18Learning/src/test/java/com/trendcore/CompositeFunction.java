package com.trendcore;

public interface CompositeFunction<I,R> {

    R apply(I i);

    default <V> CompositeFunction<V,R> compose(CompositeFunction<? super V,? extends I> function ){

        CompositeFunction<I,R> current = this;

        /*CompositeFunction<V, R> compositeFunction = new CompositeFunction<V, R>() {
            @Override
            public R apply(V v) {

                System.out.println(hashCode() + " " + current.hashCode());

                return current.apply(function.apply(v));
            }
        };*/

        CompositeFunction<V, R> compositeFunction = (V v) -> {

            System.out.println(hashCode() + " " + current.hashCode());

            return apply(function.apply(v));
        };

        return compositeFunction;
    }

}
