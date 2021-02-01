package com.kekadoc.tools.visual

interface Visible {

    interface Mutable : Visible {
        fun setVisible(visible: Boolean)
    }

    fun isVisible(): Boolean
}