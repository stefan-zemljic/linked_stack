package ch.bytecraft.linked_stack

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LinkedStackTest {

    @Test
    fun `test isEmpty and isNotEmpty on new stack`() {
        val stack = LinkedStack<Int>()
        assertThat(stack.isEmpty()).isTrue()
        assertThat(stack.isNotEmpty()).isFalse()
    }

    @Test
    fun `test isEmpty and isNotEmpty after pushing elements`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        assertThat(stack.isEmpty()).isFalse()
        assertThat(stack.isNotEmpty()).isTrue()
    }

    @Test
    fun `test isEmpty after popping all elements`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        stack.pop()
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test push and peek`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        assertThat(stack.peek()).isEqualTo(1)
        assertThat(stack.peek()).isEqualTo(1)
    }

    @Test
    fun `test pushAll and peekAll`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test peek on empty stack throws NoSuchElementException`() {
        val stack = LinkedStack<Int>()
        assertThrows<NoSuchElementException> { stack.peek() }
    }

    @Test
    fun `test peekOrNull on empty stack returns null`() {
        val stack = LinkedStack<Int>()
        assertThat(stack.peekOrNull()).isNull()
    }

    @Test
    fun `test peekOrNull after pushing elements`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        assertThat(stack.peekOrNull()).isEqualTo(1)
    }

    @Test
    fun `test peek(n) with various n values`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        assertThat(stack.peek(0)).isEmpty()
        assertThat(stack.peek(1)).isEqualTo(listOf(3))
        assertThat(stack.peek(2)).isEqualTo(listOf(3, 2))
        assertThat(stack.peek(3)).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.peek(4)).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test peekAll on empty stack`() {
        val stack = LinkedStack<Int>()
        assertThat(stack.peekAll()).isEmpty()
    }

    @Test
    fun `test peekAll on stack with elements`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test peekUntil node`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        val node = stack.head
        stack.push(2)
        stack.push(3)
        val result = stack.peekUntil(node)
        assertThat(result).isEqualTo(listOf(3, 2))
    }

    @Test
    fun `test peekUntil null node`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.peekUntil(null)
        assertThat(result).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test peekUntil action when condition is met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3, 4, 5))
        val result = stack.peekUntil { it == 3 }
        assertThat(result).isEqualTo(listOf(5, 4))
    }

    @Test
    fun `test peekUntil action when condition is never met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.peekUntil { it > 3 }
        assertThat(result).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test peekWhile action when condition is met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(5, 4, 3, 2, 1))
        val result = stack.peekWhile { it < 3 }
        assertThat(result).isEqualTo(listOf(1, 2))
    }

    @Test
    fun `test peekWhile action when condition is never met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.peekWhile { it > 3 }
        assertThat(result).isEmpty()
    }

    @Test
    fun `test pop on empty stack throws NoSuchElementException`() {
        val stack = LinkedStack<Int>()
        assertThrows<NoSuchElementException> {
            stack.pop()
        }
    }

    @Test
    fun `test pop on stack with elements`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val value = stack.pop()
        assertThat(value).isEqualTo(3)
        assertThat(stack.peekAll()).isEqualTo(listOf(2, 1))
    }

    @Test
    fun `test popOrNull on empty stack returns null`() {
        val stack = LinkedStack<Int>()
        assertThat(stack.popOrNull()).isNull()
    }

    @Test
    fun `test popOrNull on stack with elements`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        val value = stack.popOrNull()
        assertThat(value).isEqualTo(1)
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test pop(n) with various n values`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result0 = stack.pop(0)
        assertThat(result0).isEmpty()
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))

        val result1 = stack.pop(1)
        assertThat(result1).isEqualTo(listOf(3))
        assertThat(stack.peekAll()).isEqualTo(listOf(2, 1))

        val result2 = stack.pop(5)
        assertThat(result2).isEqualTo(listOf(2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test popUntil node`() {
        val stack = LinkedStack<Int>()
        stack.push(1)
        val node = stack.head
        stack.pushAll(listOf(2, 3))
        val result = stack.popUntil(node)
        assertThat(result).isEqualTo(listOf(3, 2))
        assertThat(stack.peekAll()).isEqualTo(listOf(1))
    }

    @Test
    fun `test popUntil null node`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.popUntil(null)
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test popUntil action when condition is met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3, 4, 5))
        val result = stack.popUntil { it == 3 }
        assertThat(result).isEqualTo(listOf(5, 4))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test popUntil action when condition is never met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.popUntil { it > 3 }
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test popWhile action when condition is met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(5, 4, 3, 2, 1))
        val result = stack.popWhile { it < 3 }
        assertThat(result).isEqualTo(listOf(1, 2))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 4, 5))
    }

    @Test
    fun `test popWhile action when condition is never met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.popWhile { it > 3 }
        assertThat(result).isEmpty()
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test popAll on empty stack`() {
        val stack = LinkedStack<Int>()
        val result = stack.popAll()
        assertThat(result).isEmpty()
    }

    @Test
    fun `test popAll on stack with elements`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.popAll()
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test clone shares the same nodes`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val clone = stack.clone()
        assertThat(stack.head).isSameAs(clone.head)
    }

    @Test
    fun `test modifying clone does not affect original stack`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val clone = stack.clone()
        clone.pop()
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
        assertThat(clone.peekAll()).isEqualTo(listOf(2, 1))
    }

    @Test
    fun `test forEach on empty stack`() {
        val stack = LinkedStack<Int>()
        val list = mutableListOf<Int>()
        stack.forEach { list.add(it) }
        assertThat(list).isEmpty()
    }

    @Test
    fun `test forEach on stack with elements`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val list = mutableListOf<Int>()
        stack.forEach { list.add(it) }
        assertThat(list).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test popWhile if condition is always met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.popWhile { it <= 3 }
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test popUntil if condition is never met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.popUntil { it > 3 }
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }

    @Test
    fun `test peekWhile if condition is always met`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val result = stack.peekWhile { it <= 3 }
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.peekAll()).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test peekUntil for a node that is not in the stack`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val node = LinkedStack.Node(4, null)
        val result = stack.peekUntil(node)
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(result).isEqualTo(listOf(3, 2, 1))
    }

    @Test
    fun `test popUntil for a node that is not in the stack`() {
        val stack = LinkedStack<Int>()
        stack.pushAll(listOf(1, 2, 3))
        val node = LinkedStack.Node(4, null)
        val result = stack.popUntil(node)
        assertThat(result).isEqualTo(listOf(3, 2, 1))
        assertThat(stack.isEmpty()).isTrue()
    }
}
