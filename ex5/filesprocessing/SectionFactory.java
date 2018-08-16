package filesprocessing;

import filesprocessing.filters.FilterInterface;
import filesprocessing.orders.OrderInterface;

/**
 * analyzes the command file and divides the commands into sections of one filter and one
 * order as described in the assignment.
 */
public class SectionFactory {
    private FilterInterface filter;
    private OrderInterface order;
    private int[] line;

    public SectionFactory(FilterInterface filter, OrderInterface order, int[] line) {
        this.filter = filter;
        this.order = order;
        this.line = line;
    }

    public FilterInterface getFilter() { return this.filter; }

    public OrderInterface getOrder(){ return this.order; }

    public  int[] getLine(){ return this.line; }

    public void setFilter(FilterInterface filter) { this.filter = filter; }

    public void setOrder(OrderInterface order) { this.order = order; }

    public void setLine(int[] line) { this.line = line; }
}