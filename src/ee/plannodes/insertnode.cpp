/* This file is part of VoltDB.
 * Copyright (C) 2008-2016 VoltDB Inc.
 *
 * This file contains original code and/or modifications of original code.
 * Any modifications made by VoltDB Inc. are licensed under the following
 * terms and conditions:
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with VoltDB.  If not, see <http://www.gnu.org/licenses/>.
 */
/* Copyright (C) 2008 by H-Store Project
 * Brown University
 * Massachusetts Institute of Technology
 * Yale University
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
#include "insertnode.h"

#include <sstream>

namespace voltdb {

PlanNodeType InsertPlanNode::getPlanNodeType() const { return PLAN_NODE_TYPE_INSERT; }

void InsertPlanNode::loadFromJSONObject(PlannerDomValue obj)
{
    AbstractOperationPlanNode::loadFromJSONObject(obj);
    m_multiPartition = obj.valueForKey("MULTI_PARTITION").asBool();
    if (obj.hasNonNullKey("FIELD_MAP")) {
        PlannerDomValue fieldMap = obj.valueForKey("FIELD_MAP");
        for (int i = 0; i < fieldMap.arrayLen(); ++i) {
          m_fieldMap.push_back(fieldMap.valueAtIndex(i).asInt());
        }
    }
    m_isUpsert = false;
    if (obj.hasNonNullKey("UPSERT")) {
        m_isUpsert = true;
    }
    m_sourceIsPartitioned = false;
    if (obj.hasNonNullKey("SOURCE_IS_PARTITIONED")) {
        m_sourceIsPartitioned = true;
    }
}

void InsertPlanNode::initTupleWithDefaultValues(VoltDBEngine* engine,
                                                Pool *pool,
                                                const std::set<int>& fieldsExplicitlySet,
                                                TableTuple& templateTuple,
                                                std::vector<int>& nowFields) {
    m_tcd->initTupleWithDefaultValues(pool,
                                      engine->getCatalogTable(getTargetTableName()),
                                      fieldsExplicitlySet,
                                      templateTuple,
                                      nowFields);
}

} // namespace voltdb
