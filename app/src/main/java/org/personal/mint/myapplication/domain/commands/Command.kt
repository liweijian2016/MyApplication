package org.personal.mint.myapplication.domain.commands

interface Command<out T> {
    fun execute(): T
}