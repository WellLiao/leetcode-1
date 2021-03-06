/**
 * Source : http://oj.leetcode.com/problems/copy-list-with-random-pointer
 * Author : Hexiaoqiao
 * Date   : 2014-09-26
 *
 * 0.Problem:
 * A linked list is given such that each node contains an additional 
 * random pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 * 
 * 1.Refer.:
 * 1.0 从头沿next链copy节点并附到当前节点的next；
 * 1.1 从头沿next链根据原节点的random链接副本节点之间的链接关系；
 * 1.2 副本节点从主节点脱离得到副本链表；
 */
package com.leetcode.oj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyListwithRandomPointer {
	public static class RandomListNode {
		int label;
		RandomListNode next, random;

		RandomListNode(int x) {
			this.label = x;
		}
	};
	
    public static RandomListNode copyRandomList(RandomListNode head) {
    	if (null == head) return null;
        List<RandomListNode> newList = new ArrayList<RandomListNode>();
        Map<RandomListNode, Integer> rawmap = new HashMap<RandomListNode, Integer>();
        Map<Integer, RandomListNode> dstmap = new HashMap<Integer, RandomListNode>();
        RandomListNode p = head;
        
        int i = 1;
        while (null != p) {
        	RandomListNode node = new RandomListNode(p.label);
        	newList.add(node);
        	rawmap.put(p, i);
        	dstmap.put(i, node);
        	p = p.next;
        	i = i + 1;
        }
        for (int j = 0; j < newList.size() - 1; j++) {
        	newList.get(j).next = newList.get(j + 1);
        }
        RandomListNode r = newList.get(0);
        RandomListNode q = newList.get(0);
        p = head;
        while (null != p) {
        	RandomListNode rand = p.random;
        	if (null == rand) {p = p.next; q = q.next; continue;}
        	Integer it = rawmap.get(rand);
        	RandomListNode drand = dstmap.get(it);
        	q.random = drand;
        	q = q.next;
        	p = p.next;
        }
    	return r;
    }
    
    public static void print(RandomListNode a) {
		while (null != a) {
			int next = -1;
			int rand = -1;
			if (null != a.next) next = a.next.label;
			if (null != a.random) rand = a.random.label;
			System.out.println(a.label + "(" + next + "," + rand +")");
			a = a.next;
		}
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomListNode a = new RandomListNode(1);
		RandomListNode b = new RandomListNode(2);
		RandomListNode c = new RandomListNode(3);
		RandomListNode d = new RandomListNode(4);
		RandomListNode e = new RandomListNode(5);
		RandomListNode f = new RandomListNode(6);
		RandomListNode g = new RandomListNode(7);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		e.next = f;
		f.next = g;
		a.random = c;
		b.random = e;
		c.random = null;
		d.random = a;
		e.random = b;
		f.random = g;
		g.random = c;
		
		print(a);
		RandomListNode p = copyRandomList(null);
		print(p);
	}

}
