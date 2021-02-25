package com.kekadoc.tools.content.ui

import com.kekadoc.tools.value.ValueUtils

open class SimpleLoading(val observer: ContentUI.Loading.Observer? = null) : ContentUI.Loading {

    private var active = false
    private var progress = 0.0

    override fun isShown() = active
    override fun hide() {
        active = false
        observer?.onHide()
    }
    override fun show() {
        active = true
        observer?.onShow()
    }
    override fun complete() {
        setFraction(1.0)
        observer?.onComplete()
    }

    override fun getFraction(): Double = progress

    override fun setFraction(fraction: Double) {
        ValueUtils.setValueInRange(0.0, 1.0, fraction, object : ValueUtils.RangeChangeEvents<Double> {
            override fun onChange(oldValue: Double, newValue: Double) {
                progress = newValue
                observer?.onFractionChange(this@SimpleLoading, oldValue, progress)
            }

            override fun onMax() {
                complete()
            }

            override fun onMin() {}
            override fun onOverflow(overflow: Double) {}
        })
    }

}