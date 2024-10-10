package ch.bytecraft.linked_stack

class LinkedStack<T>(
    var head: Node<T>? = null,
) {
    class Node<T>(val value: T, var prev: Node<T>?)

    fun isEmpty(): Boolean {
        return head == null
    }

    fun isNotEmpty(): Boolean {
        return head != null
    }

    fun push(value: T) {
        val node = Node(value, head)
        this.head = node
    }

    fun pushAll(values: List<T>) {
        values.forEach { push(it) }
    }

    fun peek(): T {
        val head = head ?: throw NoSuchElementException()
        return head.value
    }

    fun peek(n: Int): ArrayList<T> {
        val list = arrayListOf<T>()
        var node = head
        var i = 0
        while (node != null && i != n) {
            list.add(node.value)
            node = node.prev
            i++
        }
        return list
    }

    fun peekAll(): ArrayList<T> {
        val list = arrayListOf<T>()
        var node = head
        while (node != null) {
            list.add(node.value)
            node = node.prev
        }
        return list
    }

    fun peekOrNull(): T? {
        return head?.value
    }

    fun peekUntil(node: Node<T>?): ArrayList<T> {
        val list = arrayListOf<T>()
        var current = head
        while (current !== node && current != null) {
            list.add(current.value)
            current = current.prev
        }
        return list
    }

    fun peekUntil(action: (T) -> Boolean): ArrayList<T> {
        val list = arrayListOf<T>()
        var node = head
        while (node != null && !action(node.value)) {
            list.add(node.value)
            node = node.prev
        }
        return list
    }

    fun peekWhile(action: (T) -> Boolean): ArrayList<T> {
        val list = arrayListOf<T>()
        var node = head
        while (node != null && action(node.value)) {
            list.add(node.value)
            node = node.prev
        }
        return list
    }

    fun pop(): T {
        val head = head ?: throw NoSuchElementException()
        this.head = head.prev
        return head.value
    }

    fun popOrNull(): T? {
        val head = head ?: return null
        this.head = head.prev
        return head.value
    }

    fun pop(n: Int): ArrayList<T> {
        val list = arrayListOf<T>()
        var i = 0
        var node = head
        while (node != null && i != n) {
            list.add(node.value)
            node = node.prev
            i++
        }
        head = node
        return list
    }

    fun popUntil(node: Node<T>?): ArrayList<T> {
        val list = arrayListOf<T>()
        var current = head
        while (current !== node && current != null) {
            list.add(current.value)
            current = current.prev
        }
        head = current
        return list
    }

    fun popUntil(action: (T) -> Boolean): ArrayList<T> {
        val list = arrayListOf<T>()
        var current = head
        while (current != null && !action(current.value)) {
            list.add(current.value)
            current = current.prev
        }
        head = current
        return list
    }

    fun popWhile(action: (T) -> Boolean): ArrayList<T> {
        val list = arrayListOf<T>()
        var current = head
        while (current != null && action(current.value)) {
            list.add(current.value)
            current = current.prev
        }
        head = current
        return list
    }

    fun popAll(): ArrayList<T> {
        val list = arrayListOf<T>()
        var node = head
        while (node != null) {
            list.add(node.value)
            node = node.prev
        }
        head = null
        return list
    }

    fun clone(): LinkedStack<T> {
        return LinkedStack<T>().also { it.head = head }
    }

    fun forEach(action: (T) -> Unit) {
        var node = head
        while (node != null) {
            action(node.value)
            node = node.prev
        }
    }
}
