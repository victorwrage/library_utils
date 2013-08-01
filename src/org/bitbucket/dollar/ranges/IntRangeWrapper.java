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

public class IntRangeWrapper extends AbstractNumberRangeWrapper<Integer> {

    public IntRangeWrapper(int stopAt) {
        super((stopAt < 0) ? stopAt : 0, (stopAt < 0) ? 0 : stopAt, 1);
    }

    public IntRangeWrapper(int startFrom, int stopAt) {
        super((startFrom > stopAt) ? startFrom - 1 : startFrom,
                (startFrom > stopAt) ? stopAt - 1 : stopAt,
                (startFrom > stopAt) ? -1 : 1);
    }

    public IntRangeWrapper(Integer startFrom, Integer stopAt, Integer stepBy) {
        super(startFrom, stopAt, stepBy);
    }

    @Override
    protected AbstractNumberRangeWrapper<Integer> create(Integer startFrom,
            Integer stopAt, Integer stepBy) {
        return new IntRangeWrapper(startFrom, stopAt, stepBy);
    }

    @Override
    protected Integer add(Integer x, Integer y) {
        return x + y;
    }

    @Override
    protected Integer floorDivide(Integer x, Integer y) {
        return x / y;
    }

    @Override
    protected Integer multiply(Integer x, Integer y) {
        return x * y;
    }

    @Override
    protected Integer abs(Integer x) {
        return Math.abs(x);
    }

    @Override
    protected Integer fromInt(int x) {
        return x;
    }
    
    @Override
    protected Integer mod(Integer number, Integer modder) {
    	return number.intValue() % modder.intValue();
    }

}
