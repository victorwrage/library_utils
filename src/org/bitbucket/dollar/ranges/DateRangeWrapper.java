package org.bitbucket.dollar.ranges;

/*
 * Dollar, http://bitbucket.org/dfa/dollar
 * (c) 2010, 2011 Davide Angelocola <davide.angelocola@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.bitbucket.dollar.AbstractWrapper;
import org.bitbucket.dollar.Dollar.RangeWrapper;
import org.bitbucket.dollar.Dollar.Wrapper;
import org.bitbucket.dollar.ListWrapper;
import org.bitbucket.dollar.Preconditions;

/**
 * Implements a Date Range with ability to step through by any number of days,
 * months, years, etc.
 * 
 * @author Davide Angelocola <davide.angelocola@gmail.com>
 * @author Adam L. Davis <adam.dav@gmail.com>
 */
public class DateRangeWrapper extends AbstractWrapper<Date> implements
        RangeWrapper<Date> {

    private final Date begin;
    private final Date end;
    private final int step;
    private final int field;

    /**
     * Primary constructor defining begin, end, step and field.
     * 
     * @param begin
     *            Start date, inclusive.
     * @param end
     *            Ending date, exclusive.
     * @param step
     *            Number of units increased each iteration.
     * @param field
     *            Field to increase each iteration.
     */
    public DateRangeWrapper(Date begin, Date end, int step, int field) {
        Preconditions.requireNotNull(begin, "begin date must be non-null");
        Preconditions.requireNotNull(end, "end date must be non-null");
        Preconditions.require(step != 0, "step must not be zero");
        if (step > 0) {
            Preconditions.require(end.getTime() >= begin.getTime(),
                    "End must be after or equal begin if step is positive");
        } else {
            Preconditions.require(end.getTime() <= begin.getTime(),
                    "End must be before or equal begin if step is negative");
        }
        this.begin = begin;
        this.end = end;
        this.step = step;
        this.field = field;
        getDiff(); // throws IllegalArgEx... if not supported field.
    }

    /** Defaults to step of one day. */
    public DateRangeWrapper(Date begin, Date end) {
        this(begin, end, 1);
    }

    /** Defaults to step of "step" days. */
    public DateRangeWrapper(Date begin, Date end, int step) {
        this(begin, end, step, Calendar.DAY_OF_MONTH);
    }

    @Override
    public Iterator<Date> iterator() {
        return new DateIterator(begin, end);
    }

    @Override
    public Wrapper<Date> copy() {
        return new DateRangeWrapper(begin, end, step, field);
    }

    @Override
    public Wrapper<Date> repeat(int n) {
        final Date newEnd = addToDateUsingField(begin, step * size() * n);

        return new DateRangeWrapper(begin, newEnd, step, field);
    }

    @Override
    public int size() {
        if (begin.equals(end)) {
            return 0;
        }
        int guess = diff(begin, end);

        // deals with months and leap years
        if (step > 0) {
            while (end.before(addToDateUsingField(begin, guess * step)))
                guess--;
        } else if (step < 0) {
            while (end.after(addToDateUsingField(begin, guess * step)))
                guess++;
        }

        return guess;
    }

    private Date addToDateUsingField(final Date current, int num) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        calendar.add(field, num);
        return calendar.getTime();
    }

    private int diff(Date date, Date date2) {
        final long diffMillis = date2.getTime() - date.getTime();
        final long stepDiff = getDiff() * step;

        return (int) Math.abs(diffMillis / stepDiff);
    }

    @Override
    public Wrapper<Date> slice(int i, int j) {
        // from i to j
        if (i >= 0 && j < size()) {
            final Date begin = addToDateUsingField(this.begin, i * step);
            final Date end = addToDateUsingField(this.begin, j * step);

            return new DateRangeWrapper(begin, end, step, field);
        }
        return this;
    }

    static final int SECOND = 1000;
    static final int MINUTE = 60 * SECOND;
    static final int HOUR = 60 * MINUTE;
    static final int DAY = 24 * HOUR;
    static final int YEAR = 365 * DAY;

    protected long getDiff() {
        final long d;
        switch (field) {
        case Calendar.MILLISECOND:
            d = 1;
            break;
        case Calendar.SECOND:
            d = SECOND;
            break;
        case Calendar.MINUTE:
            d = MINUTE;
            break;
        case Calendar.HOUR_OF_DAY:
        case Calendar.HOUR:
            d = HOUR;
            break;
        case Calendar.DAY_OF_MONTH:
        case Calendar.DAY_OF_YEAR:
        case Calendar.DAY_OF_WEEK:
            d = DAY;
            break;
        case Calendar.YEAR:
            d = YEAR;
            break;
        case Calendar.MONTH:
            d = 31 * DAY; // always over-estimates
            break;
        default:
            throw new IllegalArgumentException("Unknown field:" + field);
        }
        return d;
    }

    @Override
    public Wrapper<Date> shuffle(Random random) {
        return new ListWrapper<Date>(this).shuffle();
    }

    @Override
    public Wrapper<Date> reverse() {
        return new DateRangeWrapper(end, begin, step * -1, field);
    }

    private class DateIterator implements Iterator<Date> {

        private Date current;
        private final Date end;

        public DateIterator(Date begin, Date end) {
            this.current = begin;
            this.end = end;
        }

        @Override
        public boolean hasNext() {
            return current.before(end);
        }

        @Override
        public Date next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(current);
            calendar.add(field, step);
            current = calendar.getTime();
            return current;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove() not supported for DateWrapper");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof DateRangeWrapper) {
            DateRangeWrapper dateRangeWrapper = (DateRangeWrapper) object;
            return dateRangeWrapper.begin.equals(begin)
                    && dateRangeWrapper.end.equals(end)
                    && dateRangeWrapper.step == step
                    && dateRangeWrapper.field == field;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new long[] { begin.getTime(), end.getTime(),
                step, field });
    }

    @Override
    public boolean test(Date t) {
        if (begin.equals(t)) {
            return true;
        }
        if (end.before(begin)) {
            return end.before(t) && t.before(begin);
        }
        return begin.before(t) && t.before(end);
    }

    @Override
    public boolean contains(Date it) {
        return test(it);
    }
}
