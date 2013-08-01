package org.bitbucket.dollar;

import java.util.Enumeration;
import java.util.LinkedList;

import org.bitbucket.dollar.Dollar.Wrapper;

/**
 * Really simple enumeration wrapper that just adds all the elements to a list.
 * 
 * @author Adam L. Davis
 */
public class EnumerationWrapper<E> extends ListWrapper<E> implements Wrapper<E> {

    public EnumerationWrapper(Enumeration<E> enumer) {
        super(new LinkedList<E>());
        if (!enumer.hasMoreElements())
            return;
        for (E it = enumer.nextElement(); it != null; it = enumer.nextElement()) {
            list.add(it);
            if (!enumer.hasMoreElements())
                break;
        }
    }

}
