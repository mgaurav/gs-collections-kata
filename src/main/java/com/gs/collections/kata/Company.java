/*
 * Copyright 2015 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.kata;

import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.block.factory.Predicates;
import com.gs.collections.impl.list.mutable.FastList;
import org.junit.Assert;

import java.util.List;

/**
 * A company has a {@link MutableList} of {@link Customer}s.  It has an array of {@link Supplier}s, and a name.
 */
public class Company
{
    private final String name;
    private final MutableList<Customer> customers = FastList.newList();

    // suppliers are array based.
    private FastList<Supplier> suppliers = FastList.newList();

    public Company(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void addCustomer(Customer aCustomer)
    {
        this.customers.add(aCustomer);
    }

    public MutableList<Customer> getCustomers()
    {
        return this.customers;
    }

    public MutableList<Order> getOrders()
    {
        return this.customers.flatCollect(Customer::getOrders);
    }

    public Customer getMostRecentCustomer()
    {
        return this.customers.getLast();
    }

    public void addSupplier(Supplier supplier)
    {
        // need to replace the current array of suppliers with another, larger array
        // Of course, normally one would not use an array.

        this.suppliers.add(supplier);
    }

    public FastList<Supplier> getSuppliers()
    {
        return this.suppliers;
    }

    public Customer getCustomerNamed(String name)
    {
        /**
         * Use a {@link Predicate} to find a {@link Customer} with the name given.
         */
        return this.customers.detect(Predicates.attributeEqual(Customer::getName, name));
    }
}
